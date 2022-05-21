(ns scriptore.core
  (:require [clojure.string :as str]
            [clojure.pprint :refer [pprint]]
            [pdfboxing.text :as text]
            [pdfboxing.split :as split]
            [scriptore.data :refer [shorters]]))

;; Optimization notes:
;; 1. If you want to process whole 900-pages PDF
;;    past you recommends using partially parallel map
;; 2. Make everything lazy?
;; 3. Or use transducers?

(def filename "resources/test-page.pdf")
(def filename-true "resources/dufour.pdf")
(def address-regex #"\(\d?\s?[a-zA-zĄĆĘŁŃÓŚŹŻąćęłńóśźż]+ \d+.*?\)")
(def title-regex #"[A-ZĄĆĘŁŃÓŚŹŻ]{3,}")
(def chapter-regex #"\.\n \n")

(defn get-text [filename]
  (text/extract filename))

(defn get-addrs [text]
  (re-seq address-regex text))

(defn get-title [text]
  (re-find title-regex text))


(defn swap-k-v [m]
  (for [k (keys m)
        v (vals m)]
    (zipmap v (repeat k))))


(defn v-lookup [v]
  (apply
   pprint
   shorters))

(v-lookup "Wj")

;; WIP section
(def swapped-shorters (swap-k-v shorters))

(def extracted (get-text filename))

(def splitted (split/split-pdf :input filename-true :start 1 :end 2))
(def merged-txt (str (text/extract (first splitted)) (text/extract (second splitted))))
(def chapters (str/split merged-txt chapter-regex))

(def extraction
  (map
   (juxt get-title get-addrs)
   chapters))


(defn classify-addrs [addrs]
  (reduce
   (fn [m addr]
     (let [book (-> addr
                    (str/split #" ")
                    (first)
                    (subs 1))
           grp (get (apply merge swapped-shorters) book)
           current-addrs (get m grp)
           _ (println book grp "\n")]
       (assoc m grp (conj current-addrs book))))
   {:historical [] :prophetic [] :evangelic [] :non-evangelic []}
   addrs))

(classify-addrs '("(Ps 118,3)" "(Wj 4,14)" "(Wj 15,20)"))
(pprint (into {} swapped-shorters))
(map
 (fn [[title addrs]]
   {title (first addrs)})
 extraction)
