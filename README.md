AirPproject
===========

This project has been created as student "end-toend" mini-project during studing at Bionic Univercity, Kiev

Tickets booking system for airflights.

Technologies that have been used: Java 7, Spring, Hibernate, JSF(PrimeFaces), Maven, Tomcat, MySQLServer


Description of the application:

Roles:
- Customer
- Administrator
- Analyst
- Accountant
- Security officer

Customer
1. Can choose place, date of departure and place of arrival.
2. Can receive a list of available flights.
3. Can receive amount of available tickets and price for each flight.
4. Can buy tickets for a flight.
5. Can see a total price for reserved tickets.

Administrator
1. Can receive a list of flights with amount of tickets for each flight.
2. Can add new flight to timetable.
3. Can delete flight from timetable.
4. Can add tickets to flight.
5. Can delete tickets from flight.
6. Can convert all tickets booked more than three days ago in a free status.

Accountant
1. Can get a list of sold tickets.
2. Can set a ticket sold status after it’s got a corresponding invoice.

Analyst
1. Can choose time period for the report.
2. Can see the time report with information about sold tickets during given time period.
3. Can see the destination report with information about sold tickets during given time period.

Security officer
1. Can create other staff accounts with appropriate rights.
2. Can edit other staff accounts.
3. Can delete other staff accounts.
