# multi-tenancy
Multi tenancy project 

http://blog.aliprax.me/schema-based-multitenancy/



Schema-based multi-tenancy
Multi-tenancy is a virtualization technique that allows to multiple groups for users (tenants) to share a single software instance. Dealing with multiple tenants force to decide the correct strategy to separate the data of different tenants. Such strategy has implications for cost, maintenance, security and development efforts.

There exist several strategies to implement multitenancy that differs from the level of isolation and scalability across tenants:

discriminator column: in all database tables a column tells the tenant that owns the row. Multi-tenancy is not transparent to the application, there is no isolation, difficult to scale.
schema-based multitenancy: database schemas are used to separate tenants. This approach is almost transparent to the application, i.e. queries can be written without thinking about tenants.
single database per tenant: most expensive but provides the best level of isolation and scalability.
A mixed approach that preserves scalability is to shard a single database instance across different tenants but to keep multiple databases to scale in the number of tenants.
The request flow
The process to establish a multi-tenant communication is usually composed of 3 steps:

accept the incoming connection, authenticating the user if necessary.
identifying the tenant for which the user is issuing the request.
establish a connection with the database and schema of the tenant.
Tenant identification is performed against a catalog, or a default schema, which contains connection information. A user can authenticate himself on an external service and then select the tenant by using an HTTP header or a specific Host.

