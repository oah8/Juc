package oah.project.designpattern;

/**
 * @ClassName FactoryPattern
 * @Description TODO
 * @Author _oah
 * @Date 2024.02.29 22:59
 * @Version 1.0
 */
public class FactoryPattern {
    public static void main(String[] args) {
        HuaWeiPhoneFactory factory = new HuaWeiPhoneFactory();
        Phone phone = factory.createPhone();
        phone.print();
    }
}

interface Phone {
    void print();
}


class iPhone implements Phone {
    @Override
    public void print() {
        System.out.println("iPhone");
    }
}

class HuaWeiPhone implements Phone {
    @Override
    public void print() {
        System.out.println("HuaWeiPhone");
    }
}

interface Factory {
    Phone createPhone();
}


class iPhoneFactory implements Factory {
    @Override
    public Phone createPhone() {
        return new iPhone();
    }
}

class HuaWeiPhoneFactory implements Factory {
    @Override
    public Phone createPhone() {
        return new HuaWeiPhone();
    }
}



























