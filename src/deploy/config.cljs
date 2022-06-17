(ns deploy.config
  (:require [shared.utils :refer [get-env-variable]]))

(defn load! []
  (let [environment (or (get-env-variable "DEPLOY_ENV") "dev")
        dev-env?    (= "dev" environment)]
    {:version   "0.1.0"
     :logging   {:level   (or (keyword (get-env-variable "LOGGING_LEVEL")) :debug)
                 :pretty? dev-env?}
     :artifacts {:button-token (get-env-variable "CONTRACT_ARTIFACT" :required)}
     :account   {:id     (get-env-variable "ACCOUNT_ID" :required)
                 :phrase (get-env-variable "SEED_PHRASE" :required)}
     :api       {:host (get-env-variable "WS_RPC_HOST" :required)
                 :port (get-env-variable "WS_RPC_PORT")}}))
