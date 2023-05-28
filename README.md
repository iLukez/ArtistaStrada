
# Simulazione di un artista da strada per caricature e ritratti a carboncino

Questo progetto è una simulazione di un artista da strada che esegue caricature e ritratti a carboncino. Le persone interessate a un ritratto si siedono in una delle quattro sedie disponibili e attendono il loro turno per il ritratto. Le persone intorno arrivano continuamente e aspettano che una delle quattro sedie si liberi per mettersi in attesa del ritratto. Le persone che aspettano troppo a lungo per una sedia libera rinunciano a farsi fare il ritratto e vanno via. Il meccanismo di sincronizzazione tra i processi viene implementato utilizzando i **semafori** (precisamente, un semaforo a conteggio per le sedie e un mutex per il ritratto che sta venendo realizzato).

### Descrizione dell'algoritmo

##### Main:

* All'inizio, il numero totale di clienti (variabile `numClients`) viene generato casualmente.
* Vengono inizializzati i seguenti parametri:
    * `minIntervalBetweenClients`: intervallo di tempo minimo tra l'arrivo di un cliente e il successivo.
    * `maxIntervalBetweenClients`: intervallo di tempo massimo tra l'arrivo di un cliente e il successivo.
* Viene creato un semaforo a conteggio chiamato `semaphore` con valore iniziale **4**, che rappresenta le quattro sedie disponibili.
* Viene creato un semaforo a mutua esclusione chiamato `mutex` con valore iniziale **1**, che viene utilizzato per garantire che l'artista di strada esegua un ritratto alla volta.
* Viene avviato un ciclo in cui vengono creati i clienti in modo casuale:
    * Ogni cliente ha un identificatore (`id`) generato casualmente utile agli output del programma.

##### Client

* Il cliente prova ad acquisire il semaforo `semaphore` per sedersi ed iniziare ad attendere il proprio ritratto

> *"Cliente \<id> in attesa"*

**Ora possono verificarsi due casi**

1. Se il cliente riesce ad acquisire `semaphore` entro 3 secondi, significa che una sedia è disponibile.
    * Il cliente si siede e attende il suo turno.
    * > *"Cliente \<id> si è seduto e sta aspettando"*
    * Il semaforo `mutex` viene acquisito e l'artista inizia a lavorare al ritratto del cliente (chiaramente, questo step potrebbe aver bisogno di diversi secondi per venir compiuto, poichè l'artista deve prima finire i ritratti di tutte le persone già sedute da prima).
    * > *"Ritratto \<id> iniziato"*
    * Viene eseguito il ritratto per il cliente, con un tempo di esecuzione casuale compreso tra 5 e 15 secondi.
    * > *"Ritratto \<id> finito"*
    * Il semaforo `mutex` viene rilasciato per consentire all'artista di eseguire il ritratto di altri clienti.
    * Il semaforo `semaphore` viene rilasciato per liberare la sedia occupata dal cliente.


2. Se il cliente ha aspettato più a lungo di 3 secondi per acquisire `semaphore` e non è riuscito a ottenere una sedia, rinuncia a farsi fare il ritratto.
   * > *"Il cliente \<id> ha aspettato troppo e se ne è andato"*

Il programma termina quando tutti i clienti sono stati serviti o hanno rinunciato.

#### Sincronizzazione

Come già spiegato, la sincronizzazione è stata eseguita mediante un semaforo a conteggio e uno a mutua esclusione. In questo modo si assicura che la sezione critica del programma non entri mai in una situazione di **stallo (deadlock)** o **starvation**.