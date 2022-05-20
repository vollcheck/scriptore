(ns scriptore.core
  (:require [pdfboxing.text :as text]))

(def filename "resources/test-page.pdf")
(def address-regex #"\(\d?\s?[a-zA-zĄĆĘŁŃÓŚŹŻąćęłńóśźż]+ \d+.*?\)")
(def title-regex #"[A-ZĄĆĘŁŃÓŚŹŻ]{3,}")

(defn get-text [filename]
  (text/extract filename))

(defn get-addrs [text]
  (re-seq address-regex text))

(defn get-title [text]
  (re-find title-regex text))

(def extracted (get-text filename))

(comment
  (get-title extracted)
  )
