import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    StationIndex stationIndex;
    RouteCalculator routeCalculator;
    Station[][] stations = new Station[3][3];
    @Override
    protected void setUp() throws Exception {
        /*
        * Создаем схему метро, содержащую 3 линии по 3 станции на каждой и 2 пересадки между ними
        * для упрощения восприятия для станций создадим двухмерный массив, где первый уровень-это линия
        * а второй- индекс станции
        * */
        route = new ArrayList<>();
        stationIndex = new StationIndex();

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stations[0][0] = new Station("Петровская", line1);
        stations[0][1] = new Station("Ивановская", line1);
        stations[0][2] = new Station("Сидоровская", line1);
        line1.addStation(stations[0][0]);
        line1.addStation(stations[0][1]);
        line1.addStation(stations[0][2]);

        stations[1][0] = new Station("Арбузная", line2);
        stations[1][1] = new Station("Яблочная", line2);
        stations[1][2] = new Station("Банановая", line2);
        line2.addStation(stations[1][0]);
        line2.addStation(stations[1][1]);
        line2.addStation(stations[1][2]);

        stations[2][0] = new Station("Московская", line3);
        stations[2][1] = new Station("Питерская", line3);
        stations[2][2] = new Station("Рязанская", line3);
        line3.addStation(stations[2][0]);
        line3.addStation(stations[2][1]);
        line3.addStation(stations[2][2]);

        for(int i = 0; i < stations.length; i++){
            for(int j = 0; j < stations[i].length; j++) {
                stationIndex.addStation(stations[i][j]);
            }
        }

        List<Station> connection1 = new ArrayList<>();
        connection1.add(stations[0][1]);
        connection1.add(stations[1][2]);
        List<Station> connection2 = new ArrayList<>();
        connection2.add(stations[1][2]);
        connection2.add(stations[2][2]);
        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);
        routeCalculator = new RouteCalculator(stationIndex);
        /*Создаем путь для тестирования меотда CalculateDuration*/
        route.add(stations[0][0]);
        route.add(stations[2][2]);
    }

    public void testGetShortestRoute(){
        /* Тесты станций на одной линии */
        List<Station> actual = routeCalculator.getShortestRoute(stations[0][0], stations[0][2]);
        assertEquals(3, actual.size());
        List<Station> actual_1 = routeCalculator.getShortestRoute(stations[0][0], stations[0][1]);
        assertEquals(2, actual_1.size());
        /* Ожидается 5 станций с одной пересадкой. */
        List<Station> actual2 = routeCalculator.getShortestRoute(stations[0][0], stations[1][0]);
        assertEquals(5, actual2.size());
        /* Ожидается 6 станций с двумя пересадками. */
        List<Station> actual3 = routeCalculator.getShortestRoute(stations[0][0], stations[2][0]);
        assertEquals(6, actual3.size());

    }

    public void testCalculateDuration(){
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 3.5;
        assertEquals(expected, actual);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
