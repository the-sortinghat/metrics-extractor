package com.sortinghat.metrics_extractor.domain.services.size_dimension

import com.sortinghat.metrics_extractor.domain.model.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SystemComponentsMetricTest {

    @Test
    fun `should return the number of system components`() {
        val system = ServiceBasedSystem(name = "InterSCity", description = "InterSCity")
        val services = setOf(
            Service(
                name = "Resource Adaptor",
                responsibility = "",
                module = Module("Resource Adaptor"),
                system = system
            ),
            Service(
                name = "Resource Catalogue",
                responsibility = "",
                module = Module("Resource Catalogue"),
                system = system
            )
        )

        val metricExtractor = SystemComponentsMetric()

        services.forEach { service -> service.accept(metricExtractor) }

        val expected = "2 services and 2 modules"
        val actual = metricExtractor.getResult().value

        assertEquals(expected, actual)
    }

    @Test
    fun `should handle with singular and plural when there is only one service or module`() {
        val system = ServiceBasedSystem(name = "InterSCity", description = "InterSCity")
        val services = setOf(
            Service(
                name = "Resource Adaptor",
                responsibility = "",
                module = Module("Resource Adaptor"),
                system = system
            )
        )

        val metricExtractor = SystemComponentsMetric()

        services.forEach { service -> service.accept(metricExtractor) }

        val expected = "1 service and 1 module"
        val actual = metricExtractor.getResult().value

        assertEquals(expected, actual)
    }
}