(ns deploy.utils
  (:require-macros [deploy.utils]))

(defn -js->clj+
  "For cases when built-in js->clj doesn't work."
  [x]
  (into {} (for [k (js-keys x)]
             [k (aget x k)])))

(defn env
  "Returns current env vars as a Clojure map."
  []
  (-js->clj+ (.-env js/process)))

(defn get-env-variable
  [var-name & [required?]]
  (let [var-value (get (env) var-name)]
    (if (and (empty? var-value)
             required?)
      (throw (js/Error. (str "MISSING ENV VARIABLE: " var-name " not defined in environment")))
      var-value)))
