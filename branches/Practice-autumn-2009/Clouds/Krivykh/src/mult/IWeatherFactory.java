//(с) Кривых Алексей 2009г.
//Cloud
package mult;

//Абстрактная Фабрика для погоды
interface IWeatherFactory {

   public IWind windCreate();
   public IDaylight daylightCreate();
   public ILuminary luminaryCreate();
    
}
