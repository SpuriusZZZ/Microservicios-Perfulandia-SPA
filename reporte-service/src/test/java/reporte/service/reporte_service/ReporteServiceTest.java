package reporte.service.reporte_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import reporte.service.reporte_service.entidades.Reporte;
import reporte.service.reporte_service.repositorio.ReporteRepository;
import reporte.service.reporte_service.servicio.ReporteService;

public class ReporteServiceTest {
    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll(){
        Reporte r1 = new Reporte();
        r1.setId(1);
        r1.setTipoReporte("Ventas");
        r1.setFecha(null);
        r1.setTotalProductoVenta(200);
        r1.setTotalRecaudadoProductos(2000000);
        r1.setMensaje("No se ha introducido infomación");
        r1.setInventarioId(1);

        Reporte r2 = new Reporte();
        r2.setId(2);
        r2.setTipoReporte("Ventas");
        r2.setFecha(null);
        r2.setTotalProductoVenta(500);
        r2.setTotalRecaudadoProductos(5000000);
        r2.setMensaje("Se han vendido más x producto");
        r2.setInventarioId(2);
        

        //decimos que cuando se llame a findAll(),
        //retorne una lista simulada con los usuarios
        when(reporteRepository.findAll()).thenReturn(Arrays.asList(r1,r2));

        //ejecutamos el metodo a testear
        List<Reporte> resultado = reporteService.getAll();

        //Verificamos
        assertEquals(2, resultado.size());
        assertEquals("Se han vendido más x producto", resultado.get(1).getMensaje());
    }

    @Test
    public void testGuardarReporte() {
        Reporte reporte = new Reporte();
        reporte.setTipoReporte("Inventario");
        reporte.setTotalProductoVenta(100);
        reporte.setTotalRecaudadoProductos(1000000);
        reporte.setMensaje("Reporte generado automáticamente");
        reporte.setInventarioId(3);

        when(reporteRepository.save(reporte)).thenReturn(reporte);

        Reporte resultado = reporteService.save(reporte);

        assertEquals("Inventario", resultado.getTipoReporte());
        assertEquals(1000000, resultado.getTotalRecaudadoProductos());
    }

    @Test
    @DisplayName("Debe retornar un reporte por ID")
    public void testGetReporteById() {
        Reporte reporte = new Reporte();
        reporte.setId(4);
        reporte.setMensaje("Todo en orden");

        when(reporteRepository.findById(4)).thenReturn(Optional.of(reporte));

        Reporte resultado = reporteService.getReporteById(4);

        assertNotNull(resultado);
        assertEquals("Todo en orden", resultado.getMensaje());
    }

    @Test
    public void testGetReporteByIdNoExiste() {
        when(reporteRepository.findById(999)).thenReturn(Optional.empty());

        Reporte resultado = reporteService.getReporteById(999);

        assertNull(resultado);
    }

    @Test
    public void testEliminarReporte() {
        Reporte reporte = new Reporte();
        reporte.setId(5);

        when(reporteRepository.findById(5)).thenReturn(Optional.of(reporte));

        reporteService.deleteReporteById(5);

        verify(reporteRepository).deleteById(5);
    }
}
