(ns scriptore.obsolete
  (:require [clojure.string :as str]
            [clojure.pprint :refer [pprint]]
            [pdfboxing.text :as text]
            [scriptore.data :refer [shorters]]
            [scriptore.utils :refer [dump-edn]]))

(set! *warn-on-reflection* true)

(def address-regex #"\(\d?\s?[a-zA-zĄĆĘŁŃÓŚŹŻąćęłńóśźż]+ \d+.*?\)")
;; (def title-regex #"[A-ZĄĆĘŁŃÓŚŹŻ]{3,}")
(def title-regex #"[A-ZĄĆĘŁŃÓŚŹŻ].*")
(def chapter-regex #"\.\n \n")

(defn get-chapters [^String filename]
  (-> (text/extract filename)
      (str/split chapter-regex)
      (lazy-seq)))

(defn classify-addrs [addrs]
  (reduce
   (fn [m addr]
     (let [addr (subs addr 1 (dec (count addr)))
           book (-> addr
                    (str/split #" ")
                    (first))
           grp (get shorters book)
           current-addrs (get m grp)]
       (assoc m grp (conj current-addrs addr))))
   {:historical [] :prophetic [] :evangelic [] :non-evangelic []}
   addrs))

(defn process-chapters [chapters]
  (map
   (fn [m]
     (let [title (re-find title-regex m)
           addrs (re-seq address-regex m)]
       {:title title :addresses (or (classify-addrs addrs) {})}))
   chapters))

(defn save-result [chapters]
  (dump-edn (process-chapters chapters)))

(def test-filename "resources/test-page.pdf")
(def filename
  ;; "resources/dufour.pdf"
  ;; "resources/dufour-table-simple.pdf"
  ;; "resources/dufour-table-simple-cutted.pdf"
  ;; "resources/dufour-table-with-quotes.pdf"
  "resources/dufour-table-with-quotes-cutted.pdf"
  )

;; Defined globally in order to use cache
(def chapters (get-chapters filename))

(comment
  (pprint chapters)


  )
