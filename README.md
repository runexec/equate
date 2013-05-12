Equate is a fact management library for the Clojure language.

```clojure
user> (let-equal 
       ["car" "vehicle"]
       (println "Car is a vehicle?"
                (e= "car" "vehicle")))
Car is a vehicle? true
nil
```

#### Install

```bash
git clone https://github.com/runexec/equate
cd equate; lein install
```

Lein project dependency
```clojure
[equate/equate "0.1.0-SNAPSHOT"]
```

```clojure
user> (use 'equate.core)
nil
```

#### Documentation

Create multiple associations

```clojure
user> (is= :car :vehicle)
({:vehicle :car, :car :vehicle})
user> (is= :two-door :car)
({:car :two-door, :two-door :car} {:vehicle :car, :car :vehicle})
user> (and (e= :car :vehicle)
	   (e= :car :two-door))
true
```

Order doesn't matter

```clojure
user> (is= :book :many-pages)
({:many-pages :book, :book :many-pages})	
user> (e= :book :many-pages)
true
user> (e= :many-pages :book)
true
```

Remove facts from scope

```clojure
user> (isnot= :book :many-pages)
()
user> (e= :many-pages :book)
false
user> (e= :book :many-pages)
false
```

Temprorary scoping

```clojure
user> (let-equal 
       [:apple :fruit
	:fruit :sweet
	123 321]
       (and (e= :fruit :apple)
	    (e= :sweet :fruit)
	    (e= 321 123)))
		   
true
```
