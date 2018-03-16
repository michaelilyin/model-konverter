package konverter

import com.winterbe.expekt.should
import konverter.strategy.ConstructorBasedStrategy
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class PrimitiveOneToOneSpek : Spek({

    describe("konverter should support one-to-one field mapping") {

        val converter = KonverterFactory.create()

        it("and convert string field") {
            val source = StringContainer("test")
            val target = converter.convert(source, StringContainer::class)
            target.value.should.be.equal(source.value)
        }

        it("and convert int field") {
            val source = IntContainer(1)
            val target = converter.convert(source, IntContainer::class)
            target.value.should.be.equal(source.value)
        }

        it("and convert float field") {
            val source = FloatContainer(1f)
            val target = converter.convert(source, FloatContainer::class)
            target.value.should.be.equal(source.value)
        }
    }

    describe("konverter") {

        it("should be configured") {

            val converter = KonverterFactory create {
                map from StringContainer::class to StringContainer::class configured {
                    with strategy ConstructorBasedStrategy()
                }
            }

        }

    }

})