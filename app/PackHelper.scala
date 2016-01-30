package lila.openingexplorer

trait PackHelper {
  protected def packUint16(v: Int): Array[Byte] =
    Array((0xff & (v >> 8)).toByte, (0xff & v).toByte)

  protected def packUint32(v: Long): Array[Byte] =
    packUint16((0xffff & (v >> 16)).toInt) ++ packUint16((0xffff & v).toInt)

  protected def packUint48(v: Long): Array[Byte] =
    packUint32(0xffffffff & (v >> 32)) ++ packUint16((0xffff & v).toInt)

  protected def unpackUint16(b: Array[Byte]): Int =
    b(0) << 8 | b(1)

  protected def unpackUint32(b: Array[Byte]): Long =
    unpackUint16(b).toLong << 16 | unpackUint16(b.drop(2)).toLong

  protected def unpackUint48(b: Array[Byte]): Long =
    unpackUint32(b).toLong << 32 | unpackUint16(b.drop(4)).toLong
}
