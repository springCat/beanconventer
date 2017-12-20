# beanconventer
1. copy bean property to another bean
2. copy bean list property to another bean list
3. copy bean property to map
4. copy map value to bean

use cglib,reflect asm  

Benchmark 10000000 times  
copy guava:     2985ms  
beanutils:      5042ms  
cglib:          18ms  
beanconventer:  448ms  
beanset:        17ms  
  
  
get operator in hashmap is slow than invoke class method
