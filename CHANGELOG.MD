## 2.2.1
- Added asEnumSet
- Added asHashtable
- Added asIdentityHashMap
- Added asConcurrentHashMap
- Added asCopyOnWriteArrayList
- Added asCopyOnWriteArraySet

## 2.2.0
- Complete rewrite of all methods related to maps and collections
    - asGenericMap and asGenericCollection are now type-safe and properly infer their generic types
    - asGenericMap and asGenericCollection now take a single Supplier<Map> / Supplier<Collection> if you want to use custom Maps/Collections
- Added asLinkedList
- Added new Unit Tests 

## 2.1.0
- Added DataType.asEnumMap
- Added Unit Tests