# Project to use multithreaded work

## Main goal:

- to implement thread pool work

## Subject area points:

- Assumed there are incoming objects (ships) with parameters (type, capacity).
- There is a tunnel (BlockingQueue) where ships are going to appropriate pier to be unloaded.
- There are 3 hardcoded types of ships with random capacity. Capacity value is used for time that ship is staying in a
  pier.
- There are 3 threads - each works only with one ship type.
- Threads have to wait until next outgoing from tunnel ship has appropriate type.
- Tunnel size is less than incoming ships amount.
- Thread work and waits are being logged into terminal.
