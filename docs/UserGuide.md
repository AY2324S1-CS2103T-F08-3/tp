---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# PawFection User Guide

PawFection is a **desktop app for managing animals in a pet shelter, optimized for use via a Command Line Interface** (CLI) while still
having the benefits of a Graphical User Interface (GUI). It aims to provide a platform for volunteers to store and retrieve information
about animals in a shelter easily.

<!-- * Table of Contents -->
- [Quick start](#quick-start)
--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `pawfection.jar`.

3. Copy the file to the folder you want to use as the _home folder_ for your PawFection.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar pawfection.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/Pookie i/1234 db/28-2-2022 s/male b/Golden Retriver da/23-5-2022` : Adds a dog named 'Pookie' to the Address Book.

   * `delete 3` : Deletes the animal with ID 3.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/Pookie`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/NOTES]` can be used as `n/Pookie t/Aggressive towards DOGS` or as `n/Pookie`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/NOTES]…​` can be used as `t/friendly towards human`, `t/friendly towards human t/hates walking` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME db/DOB`, `db/DOB n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Entry Management

#### Adding an animal: `add`
Adds an animal to the address book.

Format: `add n/NAME i/ID db/DOB s/SEX b/BREED da/DOA`

* ID must be a unique 4 digit number.

Examples:
* `add n/Pookie i/1234 db/28-2-2022 s/male b/Golden Retriver da/23-5-2022`
* `add n/Tofu i/1242 db/21/4/2023 s/female b/British Shorthair da/25-5-2022`


#### Adding healthStatus to animal: `addhealth`
Adds a health status to an animal in the address book.

Format: `addhealth ID [s/STERERILIZATION_STATUS]... [v/VACCINATION_STATUS]... [a/ALLERGY]... [d/DIETRY_RESTRICTION]...`

* Adds a health status to the animal at the specified `ID`.
* At least one of the parameters must be provided.
* Input values will be added on to the existing health status of the animal.

Examples:
* `addhealth 1 s/sterilized  v/Feline Lukeimia Virus v/Feline Calcivirus a/grass d/chocolate` adds sterilization status, two vaccination status, allergy and dietary restriction to the animal with ID 1.
* `addhealth 2 s/unsterilized` adds sterilization status to the animal with ID 2.

#### Removing healthStatus from animal: `removehealth`
Removes a health status from an animal in the address book.

Format: `removehealth ID [s/STERERILIZATION_STATUS_INDEX] [v/VACCINATION_STATUS_INDEX] [a/ALLERGY_INDEX] [d/DIETRY_RESTRICTION_INDEX`]

* Removes a status of the specified health status from the animal at the specified `ID`.
* At least one of the parameters must be provided.
* Input values will be removed from the existing health status of the animal.

Examples:
* `removehealth 1 s/1 v/1 a/1 d/1` removes the first sterilization status, vaccination status, allergy and dietary restriction from the animal with ID 1.
* `removehealth 2 s/3` removes the third sterilization status from the animal with ID 2.

#### Listing all animals: `list`
Shows a list of all animals in the address book.

Format: `list`

* The list is sorted by the name of the animal in alphabetical order.
* Can be use after using the `find` command to list all animals again.


#### Deleting an animal: `delete`
Deletes the specified animal from the address book.

Format: `delete ID`

* Deletes the animal at the specified `ID`.
* The ID refers to the Animal ID number.

Examples:
* `list` followed by `delete 2` still deletes the animal with ID 2.
* `find Tofu` followed by `delete 1` still deletes the animal with ID 1 which might not be the same as the animal in the find list.

#### Finding an animal: `find`
Finds animals whose names/ID contain any of the given keywords.

Format: `findn KEYWORD [MORE_KEYWORDS]…​` OR `findi KEYWORD [MORE_KEYWORDS]…​`

* findn searches for animals whose names contain any of the given keywords while findi searches for animals whose ID
contain any of the given keywords.
* The search is case-insensitive. e.g `pookie` will match `Pookie`
* Animals matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `pookie tofu` will return `Pookie` and `Tofu`


Examples:
* `findn Pookie` returns `Pookie`
* `findi 1234` returns `Pookie`

#### Clearing all entries : `clear`
Clears all entries from the address book.

Format: `clear`

#### Exiting the program : `exit`
Exits the program.

Format: `exit`


#### Saving the data

PawFection data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### Editing the data file

PawFection data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>
**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>


_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME i/ID db/DOB da/DOA s/SEX b/BREED`
**AddHealth** | `addhealth ID [s/STERERILIZATION_STATUS]... [v/VACCINATION_STATUS]... [a/ALLERGY]... [d/DIETRY_RESTRICTION]...`
**RemoveHealth** | `removehealth ID [s/STERERILIZATION_STATUS_INDEX] [v/VACCINATION_STATUS_INDEX] [a/ALLERGY_INDEX] [d/DIETRY_RESTRICTION_INDEX]`
**Clear**  | `clear`
**Delete** | `delete ID`<br> e.g., `delete 3`
**List**   | `list`
**Find**   | `find KEYWORD [MORE_KEYWORDS]…​`<br> e.g., `find Pookie`
**Help**   | `help`
