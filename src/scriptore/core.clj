(ns scriptore.core
  (:require [clojure.string :as str]
            [clojure.data.json :as json]
            [pdfboxing.text :as text]
            [scriptore.data :refer [shorters]]))

(def address-regex #"\(\d?\s?[a-zA-zĄĆĘŁŃÓŚŹŻąćęłńóśźż]+ \d+.*?\)")
(def title-regex #"[A-ZĄĆĘŁŃÓŚŹŻ]{3,}")
(def chapter-regex #"\.\n \n")

(defn get-chapters [filename]
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
  (map (fn [m]
         (let [title (re-find title-regex m)
               addrs (re-seq address-regex m)]
           {:title title :addresses (classify-addrs addrs)}))
     chapters))

(defn json-dump [filename data]
  (spit filename (json/write-str data)))

(comment
  ;; Optimization notes:
  ;; 1. If you want to process whole 900-pages PDF
  ;;    past you recommends using partially parallel map
  ;; 2. Make everything lazy?
  ;; 3. Or use transducers?

  (def filename "resources/test-page.pdf")
  (def filename-true "resources/dufour.pdf")


  (->> filename
      (get-chapters)
      (process-chapters)
      (json-dump "resources/out.json")) ;; Missing polish accents

  )
