package com.pc.线段树;

/**
 * 融合器
 */
public interface Merger<E> {
   E merge(E a ,E b);
}
