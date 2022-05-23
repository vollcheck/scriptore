(ns scriptore.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.pprint :refer [pprint]]
            [clojure.data.json :as json]
            [pdfboxing.text :as text]
            [scriptore.data :refer [shorters]]))

(def chapter-rx #"\n?HISTORYCZNE PROROCKIE EWANGELIE POZAEWANGELICZNE ")
(def title-rx #"[A-ZĄĆĘŁŃÓŚŹ ]{3,}")

(def start-addrs
  {:historical []
   :prophetic []
   :evangelic []
   :non-evangelic []})

(defn classify-addrs [addrs]
  (reduce
   (fn [m addr]
     (let [addr (str/trim addr)
           book (first (str/split addr #" " 2))
           grp (get shorters book)
           current-addrs (get m grp)]
       (assoc m grp (conj current-addrs addr))))
   start-addrs
   addrs))

(def addrs-xf
  (comp
   (mapcat (fn [line] (str/split line #";")))
   (filter #(re-find #"[A-ZĄĆĘŁŃÓŚŹ]" %))))

(defn process-chapter [chapter]
  (let [[title & addrs] (str/split-lines chapter)
        title           (str/trim (re-find title-rx title))
        addrs           (classify-addrs (sequence addrs-xf addrs))]
    {:title title :addresses addrs}))

(defn main-processing [filename]
  (let [data (-> filename
                 (text/extract)
                 (str/split chapter-rx))]
    (map
     process-chapter
     (rest data)))) ;; omitting first (empty) element


;; Aim to make processing faster,
;; although the longest parts are related to I/O
(defn main-processing-parallel [filename]
  (let [data (-> filename
                 (text/extract)
                 (str/split chapter-rx))]
    (pmap
     process-chapter
     (partition-all 32 (rest data)))))

(defn dump-edn
  ([data] (dump-edn data "results/dictionary.edn"))
  ([data filename]
   (spit filename (with-out-str (pprint data)))))

(defn dump-json
  ([data] (dump-json data "results/dictionary.json"))
  ([data filename]
   (spit filename (json/write-str data))))

(comment
  (def filename "resources/dufour-table-with-quotes.pdf")
  (def filename-test "resources/dufour-table-with-quotes-cutted.pdf")

  ;; Extracting text from PDF and saving it to a file
  ;; takes the most of used time...
  (time (text/extract filename)) ;; "Elapsed time: 6419.927246 msecs"
  (time (main-processing filename)) ;;   "Elapsed time: 6380.597203 msecs"
  (time (dump-edn (main-processing filename))) "Elapsed time: 15372.405544 msecs"

  )
