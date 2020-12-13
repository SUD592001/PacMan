package edu.ufl.cise.cs1.controllers;

import game.controllers.AttackerController;
import game.models.*;
import java.util.LinkedList;
import game.models.Attacker;
import java.util.List;

public final class StudentAttackerController implements AttackerController {
	public void init(Game game) {
	}

	public void shutdown(Game game) {
	}

	public int update(Game game, long timeDue) {
		Node pills;
		Node targetGhosts;
		Node defenderLocation;
		//Location node that retrieves location of gator.
		Node location = game.getAttacker().getLocation();

		Attacker player = game.getAttacker();
		int movement;
		int pathDistance;
        //Lists all 4 of the defender ghosts.
		List<Defender> defenders = game.getDefenders();

		Node ghostLocations = game.getCurMaze().getInitialDefendersPosition();
		List<Node> pillList = game.getPillList();
		//Stores locations of all defenders to keep track of them relative to gator.
		List<Node> vulnerableGhosts = new LinkedList<>();
		Node attackerLocation = game.getAttacker().getLocation();
		List<Node> gamePills = game.getPillList();
		Node nearestPill = game.getAttacker().getTargetNode(gamePills, true);
		Node nearestPowerPill = game.getAttacker().getTargetNode(pillList, true);
		pills = player.getTargetNode(pillList, true);
		movement = player.getNextDir(pills, true);
		if (nearestPowerPill != null) {
			movement = attackerLocation.getPathDistance(nearestPowerPill);
		}
		int pillLocation = -1;
		if (pillList.size() > 0) {
			pillLocation = game.getAttacker().getNextDir(nearestPowerPill, true);
		}
		int pillMove = game.getAttacker().getNextDir(nearestPill, true);
		for (int i = 0; i < 4; ++i) {
			//Checks to see if there are any nearby defenders that are vulnerable and if there is a power pill close by.
			if (pillList.size() > 0 && vulnerabilityOfDefender(defenders) == false) {
				if (nearbyDefender(defenders, attackerLocation)) {
					return pillLocation;
				}
				//If gator is not near a power pill and defender is nearby and vulnerable, it will search for the nearest power pill.
				if (movement > 5 && nearbyDefender(defenders, attackerLocation)) {
					return pillLocation;
				}
				//If power pill is near a gator and defender is not vulnerable, gator goes in different direction.
				if (movement < 5 && !nearbyDefender(defenders, attackerLocation)) {
					return game.getAttacker().getReverse();
				} else {
					return pillMove;
				}
			}
			//If defenders are in the lair, gator eats pills returned by pillList.
			if (defenders.get(i).getLocation() == ghostLocations) {
				pills = player.getTargetNode(pillList, true);
				movement = player.getNextDir(pills, true);
			}
			//If defenders are vulnerable gator finds the nearest vulnerable ghost and chases it.
			if (defenders.get(i).isVulnerable()) {
				vulnerableGhosts.add(defenders.get(i).getLocation());
				targetGhosts = player.getTargetNode(vulnerableGhosts, true);
				movement = player.getNextDir(targetGhosts, true);
			}
			if (nearestVulnerableDefender(defenders, attackerLocation) != null) {
				int defenderMove = game.getAttacker().getNextDir(nearestVulnerableDefender(defenders, attackerLocation), true);
				return defenderMove;
			//If defenders are not vulnerable, gator finds distance between it and defenders and moves in different direction.
			} else {
				defenderLocation = defenders.get(i).getLocation();
				pathDistance = location.getPathDistance(defenderLocation);
				if (pathDistance < 5) {
					movement = player.getNextDir(defenderLocation, false);
				}
			}

		}
		return movement;
	}
	//Method determines if defender is vulnerable in order to move gator to different location.
		public boolean vulnerabilityOfDefender (List < Defender > defenders) {
			for (int i = 0; i < defenders.size(); i++) {
				if (defenders.get(i).isVulnerable()) {
					return true;
				}
			}
			return false;
		}

//Method finds the closest vulnerable defender to gator and returns node of that defender.
		public Node nearestVulnerableDefender (List < Defender > defenders, Node attackerLocation){
			Node temp = null;
			for (int i = 0; i < defenders.size(); i++) {
				int distance = attackerLocation.getPathDistance(defenders.get(i).getLocation());
				if (defenders.get(i).isVulnerable()) {
					if (temp == null && distance > -1) {
						temp = defenders.get(i).getLocation();
					} else if (distance < attackerLocation.getPathDistance(temp) && distance != -1) {
						temp = defenders.get(i).getLocation();
					}

				}
			}
			return temp;
		}

//Method returns true if nearby defender is vulnerable and false if not.
		public boolean nearbyDefender (List < Defender > defenders, Node attackerLocation){
			for (int i = 0; i < defenders.size(); i++) {
				if (attackerLocation.getPathDistance(defenders.get(i).getLocation()) < 5) {
					return true;
				}
			}
			return false;
		}
	}










