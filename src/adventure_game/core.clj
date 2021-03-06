(ns adventure-game.core)

(def player-state
  {:room "room 1"
    :health "healthy"})

(def rooms
  [{:name "room 1"
    :forward "room 2"
    :backward "room 3"
    :description "A dark room..."}
   {:name "room 2"
    :forward "room 3"
    :backward "room 1"
    :description "A darker room..."}
   {:name "room 3"
    :forward "room 1"
    :backward "room 2"
    :description "The darkest room..."}])

(defn move [direction state]
  "I move the player to a new room"
  ;; (filter rooms)
  ;; (filter #(= (:name %) "room 2") rooms)
  (assoc state :room (get (first (filter #(= (:name %) (get state :room)) rooms)) direction))
  ;; (filter #(= (:name %) (get state :room)) rooms)
  )

(defn action [input state]
  "performing game action"
  (cond (= input "forward") (move :forward state)
        (= input "backward") (move :backward state)
        :else state)
  )

;; (defn move [direction]
;;   (cond (= direction "forward") "state"

(defn game-loop [input state]
  ;; (prn input)
  (if (not (= "exit" input))
    (let [new-state (action input state)]
    (do
    (prn state)
    (game-loop (read-line) new-state))
      )
    (prn "Exiting...")))

(defn -main [& args]
  (game-loop "start" player-state))

