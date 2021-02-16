# multi-threading-programming---The-Dining-Philosophers-problem-implementation

An implementation of one of the classic problems used to describe synchronization issues in a multi-threaded environment.<br />

problem descripotion: There are five philosophers sitting around a circular table and there are five forks for them to share, and to be able to eat, a philosopher needs to have forks in both his hands. After eating, he puts both of them down and then they can be picked by another philosopher who repeats the same cycle.<br />
The goal is to come up with a scheme that helps the philosophers achieve their goal of eating and thinking without getting "starved to death".<br />

* DiningPhilosophers.java is the main thread, which create 5 Philosopheres instances who used as threads, and assign each one of them right and left fork in a way that none of them will enter the situation of deadlock.<br />
The main thread also run another thread, the Printing thread, which run in parallel to the Philosopheres and print to screen the status of each one.<br />

* FoodSticks.java are the shared objects. There are 5 of them and there are functions that make sure that no FoodStick would be grasped by 2 philosophers at once, and manages the event (by using locks) so that whoever not holds 2 FoodStick goes into waiting, and get notified when a FoodStick becomes vacant and has the opportunity to catch one, so that no one enters a state of deadlock.<br />

* Philosopher.java is a thread, there is 5 of them which run simultaneously and שnd their task is to eat when they managed to get 2 FoodStick, release the sticks when they are done, think some random time and try again to get FoodStick and so on.<br />

* Printing.java is the another thread which run in which run in parallel to the Philosopheres and print to screen the status of each one.<br />

* run.bat is used to run the program.
