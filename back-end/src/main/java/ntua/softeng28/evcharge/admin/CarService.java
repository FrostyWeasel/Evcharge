package ntua.softeng28.evcharge.admin;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntua.softeng28.evcharge.car.AcCharger;
import ntua.softeng28.evcharge.car.AcChargerRepository;
import ntua.softeng28.evcharge.car.Brand;
import ntua.softeng28.evcharge.car.BrandRepository;
import ntua.softeng28.evcharge.car.Car;
import ntua.softeng28.evcharge.car.CarRepository;
import ntua.softeng28.evcharge.car.ChargingCurvePoint;
import ntua.softeng28.evcharge.car.ChargingCurvePointRepository;
import ntua.softeng28.evcharge.car.DcCharger;
import ntua.softeng28.evcharge.car.DcChargerRepository;

@Service
public class CarService {
    @Autowired
	CarRepository carRepository;

	@Autowired
	AcChargerRepository acChargerRepository;

	@Autowired
	ChargingCurvePointRepository chargingCurvePointRepository;

	@Autowired
	DcChargerRepository dcChargerRepository;

	@Autowired
    BrandRepository brandRepository;

    public CarService() {
    }
    
    public void saveCarsToDB(CarDataRequest carDataRequest){
        if(carDataRequest.getBrandData() != null){
            for(BrandData brandData: carDataRequest.getBrandData()){

                Brand newBrand = new Brand();

                newBrand.setId(brandData.getId());
                newBrand.setName(brandData.getName());

                brandRepository.save(newBrand);
            }
        }

        if(carDataRequest.getCarData() != null){
            for(CarData carData: carDataRequest.getCarData()){

                Car newCar = new Car();

                DcCharger dcCharger = null;
                if(carData.getDc_charger() != null){
                    Set<ChargingCurvePoint> chargingCurve = new HashSet<>();
                    
                    for(ChargingCurvePoint chargingCurvePoint: carData.getDc_charger().getCharging_curve()){
                        chargingCurve.add(chargingCurvePointRepository.save(chargingCurvePoint));
                    }

                    dcCharger = new DcCharger();
                    dcCharger.setCharging_curve(chargingCurve);
                    dcCharger.setIs_default_charging_curve(carData.getDc_charger().getIs_default_charging_curve());
                    dcCharger.setMax_power(carData.getDc_charger().getMax_power());
                    dcCharger.setPorts(carData.getDc_charger().getPorts());
                    
                    dcCharger = dcChargerRepository.save(dcCharger);
                }

                AcCharger acCharger = null;
                if(carData.getAc_charger() != null)
                    acCharger = acChargerRepository.save(carData.getAc_charger());


                newCar.setAc_charger(acCharger);
                newCar.setBrand(brandRepository.findById(carData.getBrand_id()).orElse(null));
                newCar.setId(carData.getId());
                newCar.setModel(carData.getModel());
                newCar.setRelease_year(carData.getRelease_year());
                newCar.setType(carData.getType());
                newCar.setUsable_battery_size(carData.getUsable_battery_size());
                newCar.setVariant(carData.getVariant());
                newCar.setDc_charger(dcCharger);
                newCar.setEnergy_consumption(carData.getEnergyConsumption());

                carRepository.save(newCar);
            }
        }
    }
}
