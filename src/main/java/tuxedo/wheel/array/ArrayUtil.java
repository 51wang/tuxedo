package tuxedo.wheel.array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArrayUtil {
    @SuppressWarnings("unchecked")
    public static <T> T[] arrayFilledByGenerator(@NonNull Class<T> componentType, int length, IntFunction<? extends T> generator) {
        if (length < 0) {
            throw new IllegalArgumentException("Size should be non-negative!");
        }
        T[] array = (T[]) Array.newInstance(componentType, length);
        Arrays.setAll(array, generator);
        return array;
    }

    public static <T> T[] arrayFilledBySupplier(Class<T> componentType, int length, @NonNull Supplier<? extends T> supplier) {
        return arrayFilledByGenerator(componentType, length, i -> supplier.get());
    }

    public static <T> T[] arrayFilledByValue(Class<T> componentType, int length, T value) {
        return arrayFilledByGenerator(componentType, length, i -> value);
    }

    public static <T> void reverse(T[] array) {
        final int length = array.length;
        T tmp;
        for (int i = 0; i < length / 2; i++) {
            tmp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = tmp;
        }
    }

    public static <T> T[] copyAndReverse(T[] original) {
        T[] copy = Arrays.copyOf(original, original.length);
        reverse(copy);
        return copy;
    }
}
