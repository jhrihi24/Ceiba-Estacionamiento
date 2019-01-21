describe('Demo Registro APP', function(){
    var placa, cilindraje, buscar, registrarButton, buscarButton;
    beforeEach(function(){
        browser.get('http://localhost:4200/registro');
        placa = element(by.id('placaRegistro'));
        cilindraje = element(by.id('cilindrajeRegistro'));        
        buscar = element(by.id('buscarVehiculo'));
        registrarButton = element(by.buttonText('Registrar'));
        buscarButton = element(by.buttonText('Buscar'));
    });
    it('Deberia mostrar un mensaje de error con el formato de la placa de carro', function(){
        placa.sendKeys('CBJ57C');
        registrarButton.click().then(function(){
            browser.driver.sleep(3000);
            var popup = element(by.className("alert alert-danger"));
            expect(popup.getText()).toEqual('La placa ingresada no cuenta con el formato valido.');
        });
    });
    it('Deberia mostrar un mensaje de error con el formato de la placa de moto', function(){
        placa.sendKeys('TRS852');
        cilindraje.clear().then(function(){
            cilindraje.sendKeys(150);
        });
        registrarButton.click().then(function(){
            browser.driver.sleep(3000);
            var popup = element(by.className("alert alert-danger"));
            expect(popup.getText()).toEqual('La placa ingresada no cuenta con el formato valido.');
        });
    });
    it('Deberia mostar un mensaje de exito con vehiculo ingresado', function(){
        placa.sendKeys('TRS57C');
        cilindraje.clear().then(function(){
            cilindraje.sendKeys(800);
        });
        registrarButton.click().then(function(){
            browser.driver.sleep(3000);
            var popup = element(by.className("alert alert-success"));
            expect(popup.getText()).toEqual('El veh\u00EDculo con la placa TRS57C ingresado con exito.');
        });
    });
    it('Deberia mostrar un mensaje de error con vehiculo ya ingresado', function(){
        placa.sendKeys('TRS57C');
        cilindraje.clear().then(function(){
            cilindraje.sendKeys(150);
        });
        registrarButton.click().then(function(){
            browser.driver.sleep(3000);
            var popup = element(by.className("alert alert-danger"));
            expect(popup.getText()).toEqual('Ya se encuentra un veh\u00EDculo con esa placa en el estacionamiento.');
        });
    });
    it('Deberia mostrar un mensaje de error con el cupo lleno para motos', function(){
        var placasArray=['CBJ01C', 'CBJ02C', 'CBJ03C', 'CBJ04C', 'CBJ05C', 'CBJ06C', 'CBJ07C', 'CBJ08C', 'CBJ09C', 'CBJ10C'];
       placasArray.forEach(function(index){
            placa.clear().then(function(){
                placa.sendKeys(index);
            });
            cilindraje.clear().then(function(){
                cilindraje.sendKeys(150);
            });
            registrarButton.click().then(function(){
                if(index==='CBJ10C'){
                    browser.driver.sleep(3000);                    
                    var popup = element(by.className("alert alert-danger"));
                    expect(popup.getText()).toEqual('No hay cupo para el veh\u00EDculo.');
                }
            });            
        });
    });
    it('Deberia mostrar un mensaje de error con el cupo lleno para carros', function(){
       var placasArray=['TRS001', 'TRS002', 'TRS003', 'TRS004', 'TRS005', 'TRS006', 'TRS007', 'TRS008', 'TRS009', 'TRS010',
                        'TRS011', 'TRS012', 'TRS013', 'TRS014', 'TRS015', 'TRS016', 'TRS017', 'TRS018', 'TRS019', 'TRS020',
                        'TRS021'];
       placasArray.forEach(function(index){
            placa.clear().then(function(){
                placa.sendKeys(index);
            });            
            registrarButton.click().then(function(){
                if(index==='TRS021'){
                    browser.driver.sleep(3000);              
                    var popup = element(by.className("alert alert-danger"));
                    expect(popup.getText()).toEqual('No hay cupo para el veh\u00EDculo.');
                }
            });            
        });
    });
    it('Deberia de filtrar solo por la placa TRS57C', function(){
        buscar.clear().then(function(){
            buscar.sendKeys('TRS5');
        });
        buscarButton.click().then(function(){
            var tablaBusqueda = element(by.className('table'));
            tablaBusqueda.all(by.css('tbody tr')).then(function(totalRows) { 
                expect(totalRows.length).toEqual(1);
            });
        });
    });
});