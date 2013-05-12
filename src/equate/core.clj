(ns equate.core)

(def ^:dynamic *equals* (atom {}))

(defn is= 
  "Adds an equate defition"
  [x y]
  (swap! *equals* 
         #(->> %
               (cons 
                (assoc {} x y y x))
               distinct)))

(defn isnot= 
  "Removes an is= definition"
  [x y]
  (swap! *equals*
         (fn [_]
           (->> _
                (filter
                 #(not= % (assoc {} x y y x)))))))

(defn e= [x y]
  (let [equate (assoc {} x y y x)]
    (->> @*equals*
         (filter #(= % equate))
         empty?
         not)))
      

(defmacro let-equal [xy-coll & body]
  {:pre [(and (seq xy-coll)
              (-> xy-coll count even?))]}
  `(binding [*equals* (atom {})]
     (doseq [_# (partition 2 ~xy-coll)]
       (apply is= _#))
          ~@body))
