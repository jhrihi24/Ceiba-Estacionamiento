package com.ceiba.estacionamiento.controller;

/*@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)*/
public class EstacionamientoControllerIntegrationTest {
	
	/*@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Autowired
	private EstacionamientoController estacionamientoController;
	
	private RegistrarVehiculoDTO registrarVehiculo;
	
	private Map<String, Long> param;
	
	@Before
	public void init(){
		param= new HashMap<String, Long>();
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withPlaca("TRI852").build();
	}
	
	@Test
	public void prueba(){
		assertTrue(Boolean.TRUE);
	}
	
	@Test
	public void testARegistrarVehiculo() throws EstacionamientoException, InterruptedException{		
		RespuestaDTO<String> respuestaDTO= estacionamientoController.registrarVehiculo(registrarVehiculo);
		assertTrue(respuestaDTO.isSuccess());
		assertEquals("El veh\u00EDculo con la placa "+registrarVehiculo.getPlaca()+" ingresado con exito.", respuestaDTO.getMensaje());
	}
	
	@Test
	public void testBRegistrarVehiculoPlacaDuplicada() throws EstacionamientoException, InterruptedException{	
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("Ya se encuentra un vehiculo con esa placa en el estacionamiento.");
		estacionamientoController.registrarVehiculo(registrarVehiculo);
	}
	
	@Test
	public void testCRegistrarVehiculoPlacaSinIngresar() throws EstacionamientoException{
		registrarVehiculo= new RegistrarVehiculoDTODataBuilder().withPlaca("").build();
		RespuestaDTO<String> respuestaDTO= estacionamientoController.registrarVehiculo(registrarVehiculo);
		assertFalse(respuestaDTO.isSuccess());
		assertEquals("Debe ingresar una placa", respuestaDTO.getMensaje());
	}
	
	@Test
	public void testDGetServiciosActivosSinPlaca(){
		assertEquals(1, estacionamientoController.getServiciosActivos(null).getData().size());
	}
	
	@Test
	public void testEGetServiciosActivosConPlaca(){
		assertEquals(1, estacionamientoController.getServiciosActivos("TR").getData().size());
	}
	
	@Test
	public void testFSalidaVehiculoNoEncontrado() throws EstacionamientoException{
		param.put("idServicio", 0L);
		exception.expect(EstacionamientoException.class);
		//exception.expectMessage("El servicio no existed.");
		estacionamientoController.salidaVehiculo(param);
	}
	
	@Test
	public void testGSalidaVehiculoSuccess() throws EstacionamientoException{
		param.put("idServicio", 1L);
		RespuestaDTO<String> respuestaDTO= estacionamientoController.salidaVehiculo(param);
		assertTrue(respuestaDTO.isSuccess());
	}*/
		
}
