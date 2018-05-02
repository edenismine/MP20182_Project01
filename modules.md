# Module stores

Library with the SandwichStore, SandwichSotreClerk, SandwichStoreSupervisor. 

# Package com.tormenteddan.stores

This package contains the [SandwichStore] class, which is both
[observable][java.util.Observable] and an
[observer][java.util.Observer]. In practice a sandwich store would
observe its [clerks][SandwichStore.clerks] and notify its
[supervisors][SandwichStoreSupervisor]. Both [SandwichStoreClerk]
and [SandwichStoreSupervisor] classes are included in this package as well.

# Module sandwiches

Library with a Sandwich implementation that uses the decorator pattern.

# Package com.tormenteddan.sandwiches

Package that implements sandwiches using the decorator design pattern.

# Package com.tormenteddan.sandwiches.ingredients

Package with constants used as ingredients and bread types for sandwich
objects, as well as some useful extension functions that work with them.

# Module util

Library with useful interfaces for storing and working with Inventories.

# Package com.tormenteddan.util

Package with useful data structures as well as some interfaces that make
the included classes easier to use.

# Module app

Demos how a sandwich store would work under certain circumstances.

# Package com.tormenteddan.demos

Package that contains the application that can run the demos. It works
with three sandwich stores (each one has a clerk as well) and a
supervisor.

## Demo 1

Makes each clerk prepare a random menu item from their store. The
supervisor replenishes all the stores’ inventories afterwards.

## Demo 2

It makes the clerks from Main St and Lime Drv prepare the same sandwich.
Since the clerk at Mains St works at a tortería he shouldn't add a discount
to the sandwich, but the clerk from Lime Drv should. The supervisor
replenishes all the stores’ inventories afterwards.