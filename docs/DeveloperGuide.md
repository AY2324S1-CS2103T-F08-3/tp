---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# AB-3 Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

## `AddAnimalCommand` Implementation

### Proposed Implementation

The `AddAnimalCommand` is a specific command designed to add an animal to the animal catalog. It identifies the animal to be added with attributes such as its name, ID, species, breed, age, date of birth and date of admission.

Here's a brief outline of its operations and attributes:

- `AddAnimalCommandParser#parse#` — Parses the user input to create an animal with the given attributes.
- `AddAnimalCommand#execute(AnimalModel model)` — Executes the command to add a specified animal to the model.

Given below is an example usage scenario of the `AddAnimalCommand`:

1. The user types in the `add` command with the proper cli syntax, giving a compulsory input field for each attribute. Note that the position of the cli syntax can be in any order.
2. The command verifies the validity of the index. If the index is missing any of the required syntax, it throws a `CommandException`. Otherwise, it adds the animal with its inputted attributes to the model and returns a successful command result.

The following sequence diagram shows how the `AddAnimalCommand` works:

<puml src="diagrams/AddSequenceDiagram.puml" alt="AddSequenceDiagram" />

### Design considerations:

**Aspect: How the addition is handled**

- **Alternative 1 (current choice):** Add the animal directly ALL of the attributes specified.
    - Pros: Straightforward for the user, since all attributes of the animals are handled at the start.
    - Cons: Requires many error handling in case of an invalid syntax, wrong format, or missing attributes.

- **Alternative 2:** Add the animal with mandatory attributes such as name and id but optional attributes for the rest.
    - Pros: Can be more intuitive if the user does not know all the information of the animal.
    - Cons: Have to create an optional field those attributes which require.  This can involve additional development work to allow users to input optional data as needed

_{more aspects and alternatives to be added}_

## `DeleteAnimalCommand` Implementation

### Proposed Implementation

The `DeleteAnimalCommand` is a specific command designed to remove an animal from the animal catalog. It identifies the animal to be deleted based on its displayed index in the list.

Here's a brief outline of its operations and attributes:

- `DeleteAnimalCommand#execute(AnimalModel model)` — Executes the command to delete a specified animal.
- `targetIndex` — An attribute that holds the index of the animal to be deleted.

Given below is an example usage scenario of the `DeleteAnimalCommand`:

1. The user views the list of animals in the animal catalog. Note that this can be a filtered list, such as after using the `find` command.
2. The user decides to delete a specific animal and executes the `delete` command, providing the index of the animal to be deleted. For instance, `delete 3` would aim to delete the third animal on the list.
3. The command verifies the validity of the index. If the index is out of bounds, it throws a `CommandException`. Otherwise, it retrieves the animal corresponding to the index, removes it from the model, and returns a successful command result.

The following sequence diagram shows how the `DeleteAnimalCommand` works:

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="DeleteSequenceDiagram" />

### Design considerations:

**Aspect: How the deletion is handled**

- **Alternative 1 (current choice):** Use an index to specify which animal to delete.
    - Pros: Straightforward for the user, especially if the list of animals is displayed.
    - Cons: Requires error handling in case of an invalid index.

- **Alternative 2:** Delete by specifying the animal's unique identifier or name.
    - Pros: Can be more intuitive if the user knows the specific animal's details.
    - Cons: Might lead to errors if multiple animals have similar names or if the user misspells the identifier.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**: Volunteers at animal shelters.

**Value proposition**: Our application aims to provide a centralized platform to ensure efficient storage and access to critical details, enhancing the volunteer experience in animal shelters.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| **Priority** | **As a...**          | **I want to...**                          | **So that I can...**                  |
|--------------|----------------------|-------------------------------------------|---------------------------------------|
| `***`        | new volunteer        | view specific information about each pet. | understand their unique needs.        |
| `***`        | new volunteer        | be able to access a user manual.          | easily learn how to navigate the app. |
| `***`        | volunteer            | view all animals in a single page.        | -                                     |
| `***`        | authorized volunteer | add new animal entries                    | -                                     |
| `***`        | authorized volunteer | update existing animal profiles           | -                                     |


### Use cases

(For all use cases below, the **System** is the `AnimalCatalog` and the **Actor** is the `user`, unless specified
otherwise)

---

**Use Case: UC01 - Add Animal**

**MSS:**
1. User chooses to add an animal.
2. AnimalCatalog prompts for the animal details.
3. User provides the necessary animal details.
4. AnimalCatalog validates the details.
5. AnimalCatalog adds the pet to the shelter database and displays the success message.
   Use case ends.

**Extensions:**
3a. AnimalCatalog detects that the name is null.
3a1. AnimalCatalog displays the failure message: "Please input an animal name".
Use case resumes from step 2.

---

**Use Case: UC02 - List Animals**

**MSS:**
1. User chooses to list all animals.
2. AnimalCatalog retrieves all animals from the shelter database.
3. AnimalCatalog displays the list of all animals.
   Use case ends.

---

**Use Case: UC03 - Edit Animal**

**MSS:**
1. User chooses to edit an animal's details.
2. AnimalCatalog prompts for the index of the animal to be edited.
3. User provides the index and the updated details.
4. AnimalCatalog validates the details and the index.
5. AnimalCatalog updates the animal details and displays the success message.
   Use case ends.

**Extensions:**
3a. AnimalCatalog detects that the given index is out of range.
3a1. AnimalCatalog displays a failure message indicating an invalid index.
Use case resumes from step 2.

---

**Use Case: UC04 - Delete Animal**

**MSS:**
1. User chooses to delete an animal.
2. AnimalCatalog prompts for the index of the animal to be deleted.
3. User provides the index.
4. AnimalCatalog validates the index.
5. AnimalCatalog deletes the animal and displays the success message.
   Use case ends.

**Extensions:**
3a. AnimalCatalog detects that the given index is out of range.
3a1. AnimalCatalog displays a failure message indicating an invalid index.
Use case resumes from step 2.

*a. At any time, User chooses to cancel the deletion.
*a1. AnimalCatalog requests to confirm the cancellation.
*a2. User confirms the cancellation.
Use case ends.

---

### Non-Functional Requirements

1. The application should respond to user interactions (e.g., searches, edits) within 2 seconds.
2. The application should handle up to 200 contacts efficiently
3. The application should load GUI components and data within 5 seconds upon startup.
4. The application should adapt to various screen sizes and orientations to provide an optimal user experience on different devices
5. The application should be able to scale to accommodate an increasing number of users without significant changes to the system architecture.
6. The application user interface should be intuitive, requiring minimal training for new users.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Authorized volunteers**: Experienced volunteers who have been given a passcode to access limited features of the app.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
