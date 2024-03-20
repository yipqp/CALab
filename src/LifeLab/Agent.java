package LifeLab;

import java.awt.Color;
import java.util.Set;

import CALab.Cell;

public class Agent extends Cell {
    private int status; // 0 = dead, 1 = alive
    private int ambience; // Number of living neighbors

    public Agent() {
        // Constructor
        this.reset(true); // Initialize with random status
    }

    @Override
    public void observe() {
        // Count the number of living neighbors
        ambience = 0;
        for (Cell neighbor : neighbors) {
            if (((Agent) neighbor).getStatus() == 1) {
                ambience++;
            }
        }
    }

    @Override
    public void interact() {
        // No interaction phase in Life Lab
    }

    @Override
    public void update() {
        // Update status based on the rules of Life
        if (status == 1) {
            if (Society.death.contains(ambience)) {
                status = 0; // Cell dies due to overpopulation or underpopulation
            }
        } else {
            if (Society.rebirth.contains(ambience)) {
                status = 1; // Cell comes back to life due to optimal conditions
            }
        }
        notifySubscribers();
    }

    @Override
    public void nextState() {
        status = (status + 1) % 2;
    }

    @Override
    public void reset(boolean randomly) {
        // Reset status randomly or to dead
        if (randomly) {
            status = (Math.random() < 0.5) ? 1 : 0; // Randomly set status to alive or dead
        } else {
            status = 0; // Reset to dead
        }
        notifySubscribers();
    }

    @Override
    public int getStatus() {
        return status;
    }

    public int getAmbience() {
        return ambience;
    }

    @Override
    public Color getColor() {
        // Color representation based on status
        return (status == 1) ? Color.GREEN : Color.RED;
    }
}
