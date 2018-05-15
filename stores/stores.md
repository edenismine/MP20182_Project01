## Module stores

Library with the SandwichStore, SandwichStoreClerk, SandwichStoreSupervisor.

# Package com.tormenteddan.stores

This package contains the [SandwichStore] class, which is both
[observable][java.util.Observable] and an
[observer][java.util.Observer]. In practice a sandwich store would
observe its [clerks][SandwichStore.clerks] and notify its
[supervisors][SandwichStoreSupervisor]. Both [SandwichStoreClerk]
and [SandwichStoreSupervisor] classes are included in this package as well.