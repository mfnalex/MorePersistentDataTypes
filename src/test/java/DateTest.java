import de.jeff_media.morepersistentdatatypes.GenericDataType;
import org.bukkit.persistence.PersistentDataType;

import java.util.Date;
import java.util.function.Function;

public class DateTest {

    PersistentDataType<Long, Date> dateType = new GenericDataType<>(Long.class, Date.class, new Function<Long, Date>() {
        @Override
        public Date apply(Long aLong) {
            return new Date(aLong);
        }
    }, new Function<Date, Long>() {
        @Override
        public Long apply(Date date) {
            return date.getTime();
        }
    });
}
