(ns shared.files
  (:require ["fs" :as fs]))

(defn read-json
  "read JSON and return EDN"
  [path]
  (-> (fs/readFileSync path "utf8")
      js/JSON.parse
      (js->clj :keywordize-keys true)))

(defn write-file [content file]
  (.writeFileSync fs file content))
