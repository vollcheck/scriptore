(ns scriptore.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [net.cgrand.enlive-html :as html]
            [scriptore.data :refer [shorters]]))

(def addrs-path
  [:table#Temat.table.table-bordered.table-condensed :tr :td])

(def addrs-xf
  (comp
   (map html/text)
   (remove #(or (empty? %) (< (count %) 4)))))

(defn get-content [^java.io.File f]
  (html/select
   (-> f html/html-resource first)
   addrs-path))

(defn clean-title [^String s]
  (if s
    (-> s
        (str/replace #"Temat: " "")
        (str/replace #" Osoba:1 z 1" ""))
    (println s)))

(def start-addrs
  {:historical []
   :prophetic []
   :evangelic []
   :non-evangelic []})

(defn classify-addrs [addrs]
  (reduce
   (fn [m addr]
     (let [book (first (str/split addr #" " 2))
           grp (get shorters book)
           current-addrs (get m grp)]
       (assoc m grp (conj current-addrs addr))))
   start-addrs
   addrs))

(defn save-pretty [^String filename data]
  (spit filename (with-out-str (clojure.pprint/pprint data))))

(defn parse-file
  "Main function for parse single HTML file."
  [filename & options]
  (let [[title & addrs] (sequence addrs-xf (get-content filename))
        title (clean-title title)
        addrs (classify-addrs addrs)
        result {:title title :addresses addrs}
        save? (get (first options) :save)]
    (when save?
      (save-pretty (str "out/" title ".edn") result))
    result))

(def files (rest (file-seq (io/file "resources/"))))

(defn parse-files [files]
  (map #(parse-file % {:save true}) files))
