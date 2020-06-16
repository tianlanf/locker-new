package com.tw;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class PrimaryLockerRobotTest {
    @Test
    public void should_return_ticket_and_save_in_1st_locker_when_save_bag_given_robot_manage_two_lockers_both_have_capacity() {
        Locker firstLocker = new Locker(6);
        PrimaryLockerRobot robot = new PrimaryLockerRobot(asList(firstLocker, new Locker(10)));

        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, firstLocker.pickUpBy(ticket));
    }

    @Test
    public void should_return_ticket_and_save_in_2nd_locker_when_save_bag_given_robot_manage_two_lockers_1st_is_full_2nd_has_capacity() {
        Locker secondLocker = new Locker(10);
        PrimaryLockerRobot robot = new PrimaryLockerRobot(asList(new Locker(1), secondLocker));
        robot.save(new Bag());

        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, secondLocker.pickUpBy(ticket));
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_save_bag_given_both_lockers_are_full() {
        PrimaryLockerRobot robot = new PrimaryLockerRobot(asList(new Locker(1), new Locker(1)));
        robot.save(new Bag());
        robot.save(new Bag());

        robot.save(new Bag());
    }

    @Test
    public void should_return_bag_when_pickup_bag_given_valid_ticket() {
        PrimaryLockerRobot robot = new PrimaryLockerRobot(asList(new Locker(1), new Locker(1)));
        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        Bag bag = robot.pickUpBy(ticket);
        assertSame(myBag, bag);
    }
}
