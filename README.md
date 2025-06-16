## Overview
This project is a personal project I did with the purpose of improving my understanding of state management, concurrency primitives (refs, agents), and modular application structure. It is not intended for production use. However, if you wish to experiment with or extend this project, please see the instructions below.

## Features
- Account Management: Create, deposit, withdraw, and transfer funds between accounts.

- Concurrency: Uses Clojure's STM (ref, dosync) for safe, atomic operations.

- Audit Logging: All actions are logged asynchronously using Clojure agents.

- Interactive CLI: Terminal-based menu system for user interaction.

## Usage

### Prerequisites:
- `Leiningen` installed
- `Java` (JDK 8 or later) 

### Running from source

1. Clone Repo:

`git clone <repo>`

2. Run Application:

 `lein run`

This will launch the terminal menu interface

## Building & Running the Jar

1. Build UberJar:

`lein uberjar`

This will create a jar file in the target/uberjar directory

2. Run the jar: 

`java -jar target/uberjar/banking-simulator-0.1.0-standalone.jar`

The CLI menu will appear in the terminal. 

## Disclaimer

This project is for educational purposes only. There are no security guarantees, and it is not intended for real banking or financial use. Contributions, suggestions, and forks are welcome for learning and experimentation

## License

Copyright © 2025 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

