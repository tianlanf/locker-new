package com.tw;

//        4. Given robot管理两个locker，且两个locker都存满了; When robot存包，Then 存包失败，提示locker已满
//        5. Given robot管理两个locker，且拿到有效票; When robot取包，Then 取包成功
//        6. Given robot管理两个locker，且拿到伪造票; When robot取包，Then 取包失败，提示非法票据

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class SmartLockerRobotTest {
    @Test
    public void should_return_ticket_and_save_in_1st_locker_when_save_bag_given_robot_manage_two_lockers_1st_capacity_is_3_and_2nd_is_2() {
        Locker firstLocker = new Locker(3);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, new Locker(2)));

        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, firstLocker.pickUpBy(ticket));
    }

    @Test
    public void should_return_ticket_and_save_in_2nd_locker_when_save_bag_given_robot_manage_two_lockers_1st_capacity_is_2_and_2nd_is_3() {
        Locker secondLocker = new Locker(3);
        SmartLockerRobot robot = new SmartLockerRobot(asList(new Locker(2), secondLocker));

        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, secondLocker.pickUpBy(ticket));
    }

    @Test
    public void should_return_ticket_and_save_in_1st_locker_when_save_bag_given_robot_manage_two_lockers_1st_capacity_is_2_and_2nd_is_2() {
        Locker firstLocker = new Locker(2);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, new Locker(2)));

        Bag myBag = new Bag();
        Ticket ticket = robot.save(myBag);

        assertNotNull(ticket);
        assertSame(myBag, firstLocker.pickUpBy(ticket));
    }

    @Test(expected = LockerIsFullException.class)
    public void should_throw_LockerIsFullException_when_save_bag_given_robot_manage_two_lockers_1st_capacity_is_0_and_2nd_is_0() {
        SmartLockerRobot robot = new SmartLockerRobot(asList(new Locker(0), new Locker(0)));

        robot.save(new Bag());
    }
}
