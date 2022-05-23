# scriptore

## Goals

### Process

Process source (messy PDFs) to generate structure accessible
for further application processing (webdev?).

### Platform agnostic (let's use more buzzy words)

Ability to make said structure available in different formats

- EDN - as we're coming from Clojure world (superior to JSON tbh from CLJ perspective)
- JSON - as everybody else loves it

Both of these files are available in `out/` directory.

## Optimization notes

How to speed up I/O? - this is the bottleneck right now...

- Extracting text (using pdfboxing library) from original file - *6419.927246 msecs*
- Dumping to EDN file - *8991.808341 msecs*

I might perform operations in the middle of file extraction?

Look into the following source code from used [`pdfboxing`](https://github.com/dotemacs/pdfboxing/blob/master/src/pdfboxing/text.clj) library:

```clojure
(defn extract
  "get text from a PDF document"
  [pdfdoc]
  (with-open [doc (common/obtain-document pdfdoc)]
    (-> (PDFTextStripper.)
        (.getText doc))))
```
