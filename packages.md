# Package com.tormenteddan.storecontrol

Package that contains the application that demos the sandwich model. As well 
as all the other necessary packages.

# Package com.tormenteddan.storecontrol.sandwiches

Package that implements sandwiches using the decorator design pattern.

# Package com.tormenteddan.storecontrol.stores

This package contains the [SandwichStore] class, which is both 
[observable][java.util.Observable] and an [observer][java.util.Observer]. In 
practice a sandwich store would observe its [clerks][SandwichStore.clerks] 
and notify its [supervisors][SandwichStoreSupervisor]. Both 
[SandwichStoreClerk] and [SandwichStoreSupervisor] classes are included in 
this package.

# Package com.tormenteddan.storecontrol.sandwiches.ingredients

Package with constants used as ingredients and bread types for sandwich 
objects, as well as some useful extension functions that work with them.

# Package com.tormenteddan.storecontrol.util

Package with useful data structures as well as some interfaces that make the 
included classes easier to use.
