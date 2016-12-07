# Traveling-Salesman-Problem-SET09117

Algorithms: Nearest Neighbour, Repetitive Nearest Neighbour, Two-Opt.

The Repetitive Nearest Neighbour algorithm will run the nearest neighbour algorithm having as the starting city every city.
It has the worst running time O(n^3) but can give better result without taking too much time when the number of cities is below 500.

Two-Opt makes local changes between two edges if it makes the total tour distance shorter. It tries all possible edges.

The best results both in total time taken and total distance, can be achieved by using NN and then by optimising it with Two-Opt.

The best results regarding total distance, can be achieved by using RNN and then by optimising it with Two-Opt.
