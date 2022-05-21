(ns scriptore.utils
  (:require [clojure.string :as s]
            [clojure.reflect :refer [reflect]])
  (:import [org.apache.pdfbox.pdmodel PDDocument]
           [org.apache.pdfbox.text PDFTextStripper]
           [java.io ByteArrayOutputStream
            OutputStreamWriter BufferedReader StringReader]))


(defn swap-k-v [m]
    (let [ks (keys m)
          vs (vals m)]
      (for [k ks v vs]
        (zipmap v (repeat k)))))

(defn- extract-lines [filename]
  (try
    (let [baos (ByteArrayOutputStream.)]
      (with-open [pd (PDDocument/load (java.io.File. filename))
                  osw (OutputStreamWriter. baos)]
        (.writeText (PDFTextStripper.) pd osw))
      (-> (.toString baos "UTF-8")
          StringReader.
          BufferedReader.
          line-seq))
    (catch Exception e
      (println e))))

(defn list-members [object]
  (->> object
       reflect
       :members
       (map #(:name %))))

(defn list-getters [object]
  (filter
   (fn [member] (s/starts-with? member "get"))
   (list-members object)))
