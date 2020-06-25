package com.example.masterfruits;

public class Fruit {
    final private String m_sName;
    protected Boolean m_bIsWithSeed = false;
    protected Boolean m_bIsPeelable = false;

    Fruit(String name){
        m_sName = name;
    }

    public boolean isSame(String fruit) {
        return fruit.contentEquals(m_sName.toLowerCase());
    }

    public static void getHint1Info()
    {
        System.out.println("The distribution of fruits with seed is as follow");
    }
    public String getHint1() {
        return m_bIsWithSeed.toString();
    }

    public static void getHint2Info()
    {
        System.out.println("The distribution of peelable fruits is as follow");
    }
    public String getHint2() {
        return m_bIsPeelable.toString();
    }
}
