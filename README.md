The dev-team uses an internal tool to manage teammates. The current implement is very old
fashioned and should be outsourced into a small mircoservice which will provide a communication
interface.
The team administration service should be developed in a mircoservice with Java 8 and Spring Boot.

Task 1)
The micro-service should provide a REST interface for creating, listing, editing and deleting a user
entity. As persistence the internal H2 database can be used.
The user model should contain the following properties for the sake of simplicity
- id
- firstname
- surname
- position
- GitHub profile url

Task 2)
A separate interface (for example, / users /: id / repositories) is used to output an overview of the
Git-repositories as well as their programming language. The query of the Git-repositories can be
done at runtime.
Also take into account that the communication should be reused in other controller or services.

The micro-service can be based on the sandbox provided by remind.me. The sandbox has a default
configuration for developing REST services.
Remind.me REST sandbox: https://github.com/remind-me-gmbh/remind-me-rest-sandbox
Github API Documentation: https://developer.github.com/v3/
Note: The Github API has a rate limit of 60 requests / hour for unauthenticated requests
(https://developer.github.com/v3/#rate-limiting). For this reason, it is recommended to enter a
configurable auth-token in the Github client.