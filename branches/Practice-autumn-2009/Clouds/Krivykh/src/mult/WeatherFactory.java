//(с) Кривых Алексей 2009г.
//Cloud
package mult;

//фабрика для погоды
public class WeatherFactory implements IWeatherFactory {

    //Создаем ветер
    public IWind windCreate() {
        return new Wind();
    }

    //Создаем время суток
    public IDaylight daylightCreate() {
        return new Daylight();
    }

    //Создаем светило
    public ILuminary luminaryCreate() {
        return new Luminary();
    }
}
