-- Precios --
Insert into PRECIOS (tipo_vehiculo, tipo_cobro, precio) VALUES (1, 1, 500);
Insert into PRECIOS (tipo_vehiculo, tipo_cobro, precio) VALUES (1, 2, 4000);
Insert into PRECIOS (tipo_vehiculo, tipo_cobro, precio) VALUES (2, 1, 1000);
Insert into PRECIOS (tipo_vehiculo, tipo_cobro, precio) VALUES (2, 2, 8000);

-- Configuraciones Ingreso --
Insert into CONFIGURACIONES_INGRESO (tipo_vehiculo, valor, tipo_validacion, prohibicion_dias) VALUES (1, 'A', 1, 'D-L');
Insert into CONFIGURACIONES_INGRESO (tipo_vehiculo, valor, tipo_validacion, prohibicion_dias) VALUES (2, 'A', 1, 'D-L');

-- Configuraciones cilindraje --
Insert into CONFIGURACIONES_CILINDRAJE (tipo_vehiculo, cilindraje, cobro_adicional) VALUES (1, 500, 2000);