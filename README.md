# FormulaStudent

Small project developed in Java designed to run in the IDE's console. It works like a basic control manager of cars, garages, races and tournaments. 
It gives the user the chance to create his own cars and garages, and to design simple races and even tournaments. Sample cars, garages, races and tournaments are provided by default.

To use this program, the user must clone the repository locally, and then execute it with his default IDE (running this program in the Eclipse IDE is recommended).

The program is divided in 3 sections: 

  - Manage Garages
  1. Get registered garages (only their name).
  2. Display garages details. Select an option from the list to get the car's brand + model of the garage.
  3. Create new garage providing its name.
  4. Edit a garage from the list. It allows the user to modify the name of the garage, add a car to the garage (providing brand + model of the car) and removing a car from the garage.
  5. Remove an existing garage from the list.

  - Manage Races
  1. Get registered races, displayin their name, length and number of laps.
  2. Get race details when select an option from the list. It shows garages and cars registered in the race, if any.
  3. Start a single race from the list (if any garage is registered). Every car in the garages that are registered in the race will participate. They accelerate and brake randomly,
     when the race is finished standings will be printed by display (total time + speed average), and the amount of points earned in the race.
  4. Add new race providing its name, lap length and number of laps.
  5. Edit a race from the list. Modify race's name, lap length, number of laps. Also add or remove garages from the race (only garages in the race will participate).
  6. Remove a race from the list.
  
  - Manage Tournaments
  1. Get existing tournaments (only their name).
  2. Show tournament details: races of the tournament (name + total length) + registered garages (name + points) + cars of each garage (name + points).
  3. Start tournament, starting each race registered one by one.
  4. Create new tournament (providing its name).
  5. Edit a tournament from the list. It allows the user to modify its name, and add or remove a race or a garage from the tournament.
  6. Removing an existing tournament.
  7. Show the score of each garage and car, despite of being registered in any tournament or not. 
