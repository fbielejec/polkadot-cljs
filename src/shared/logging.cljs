(ns shared.logging
  (:require [taoensso.timbre :as timbre]))

#_(defn log-field?
  "Function to determine whether to log a given field"
  [field-name _]
  (contains? #{:file :line} field-name))

(defn start! [config]
  (let [{:keys [level pretty?]} (:logging config)]
    (timbre/merge-config!
      {:min-level      (keyword level)
       :timestamp-opts {:pattern "yyyy-MM-dd'T'HH:mm:ssX"}})))
