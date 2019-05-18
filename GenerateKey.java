import java.util.Calendar;

/**
 * 方法抽取自 sharding-jdbc生成id模块
 * @author Li-C 2019年4月20日
 *
 */
public class GenerateKey {
    
    
    public static final long EPOCH;

    private static final long SEQUENCE_BITS = 12L;

    private static final long WORKER_ID_BITS = 10L;

    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;
    private static long sequence;
    private static long lastMilliseconds;
    private static byte sequenceOffset;
    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }
    /**
     * 生成唯一标识
     * @author Li-C 2019年4月20日
     * @return
     */
    public static Long genId(){
        return generateKey().longValue();
    }
    
    private synchronized static Number generateKey() {
        long currentMilliseconds = System.currentTimeMillis();
        if (lastMilliseconds == currentMilliseconds) {
            if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
                long result = System.currentTimeMillis();
                while (result <= currentMilliseconds) {
                    result = System.currentTimeMillis();
                }
                currentMilliseconds = result;
            }
        } else {
            sequenceOffset = (byte) (~sequenceOffset & 1);
            sequence = sequenceOffset;
        }
        lastMilliseconds = currentMilliseconds;
        return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (WORKER_ID_MAX_VALUE << WORKER_ID_LEFT_SHIFT_BITS)
                | sequence;
    }
    
}
