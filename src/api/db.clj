(ns api.db)

(def registros
  (atom []))

(defn limpar []
  (reset! registros []))

(defn transacoes []
  @registros)

;(defn- despesa? [transacao]
;  (= (:tipo transacao) "despesa"))
;
;(defn- calcular [acumulado transacao]
;  (let [valor (:valor transacao)]
;    (if (despesa? transacao)
;      (- acumulado valor)
;      (+ acumulado valor))))

(defn- aplicacao [acumulado transacao]
  (let [valor (* (:cotacao transacao) (:quantidade transacao))]
    (- acumulado valor)))

(defn listaAplicacoes []
  (transacoes))

(defn saldo []
  (reduce aplicacao 1000 @registros))
(defn registrar [transacao]
  (if (>= (saldo) (* (:cotacao transacao) (:quantidade transacao)))
    (let [colecao-atualizada (swap! registros conj transacao)]
      (merge transacao {:id (count colecao-atualizada)}))
    (str "Operacao invalida, saldo insuficiente!")))