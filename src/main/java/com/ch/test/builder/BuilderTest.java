package com.ch.test.builder;

import lombok.Data;

/**
 * @ClassName: BuilderTest
 * @Description: 构建者模式测试类
 * @Author: caihao
 * @Date: 2019/9/4 11:02
 */
public class BuilderTest {
    public static void main(String[] args) {
        BuilderInterface builder = new ComputerBuilder();
        Director director = new Director();
        director.construct(builder);
        Computer computer = builder.getComputer();
        System.out.println(computer);
    }
}

/**
 * Builder 构建者接口
 */
interface BuilderInterface{

    /**
     * 组装cpu
     */
    void buildCpu();

    /**
     * 组装主板
     */
    void buildMainBoard();

    /**
     * 组装内存
     */
    void buildMemory();

    /**
     * 组装硬盘
     */
    void buildDesk();

    /**
     * 组装电源
     */
    void buildPower();

    /**
     * @Description 获取Computer成品
     * @Author caihao
     * @Date 2019/9/4 11:16
     * @Param []
     * @Return com.ch.test.builder.Computer
     */
    Computer getComputer();

}

/**
 * 具体构建者类
 */
class ComputerBuilder implements BuilderInterface{

    private  Computer computer = new Computer();

    @Override
    public void buildCpu() {
        computer.setCpu("组装 inter 酷睿 i7 处理器");
    }

    @Override
    public void buildMainBoard() {
        computer.setMainBoard("组装大型主板");
    }

    @Override
    public void buildMemory() {
        computer.setMemory("组装16G内存");
    }

    @Override
    public void buildDesk() {
        computer.setDesk("组装 1T 固态硬盘");
    }

    @Override
    public void buildPower() {
        computer.setPower("组装三星电源");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}

/**
 * 成品类
 */
@Data
class Computer{

    public String cpu;
    public String mainBoard;
    public String memory;
    public String desk;
    public String power;

}

/**
 * 组装类
 */
class Director{
    public void construct(BuilderInterface bulider){
        bulider.buildCpu();
        bulider.buildMainBoard();
        bulider.buildMemory();
        bulider.buildDesk();
        bulider.buildPower();

    }
}



