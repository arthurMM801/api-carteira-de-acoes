(ns api.transacoes)

(defn valida? [transacao]
  (and (contains? transacao :cotacao)
       (number? (:cotacao transacao))
       (pos? (:cotacao transacao))
       (contains? transacao :tipo)
       (or (= "ON" (:tipo transacao))
           (= "PN" (:tipo transacao)))
       (contains? transacao :acao)
       (contains? transacao :quantidade)
       (number? (:quantidade transacao))
       (pos? (:quantidade transacao))))
