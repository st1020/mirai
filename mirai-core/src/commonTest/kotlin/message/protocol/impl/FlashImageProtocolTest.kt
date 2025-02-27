/*
 * Copyright 2019-2022 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/dev/LICENSE
 */

package net.mamoe.mirai.internal.message.protocol.impl

import net.mamoe.mirai.contact.MemberPermission
import net.mamoe.mirai.internal.message.protocol.MessageProtocol
import net.mamoe.mirai.utils.hexToBytes
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class FlashImageProtocolTest : AbstractMessageProtocolTest() {
    override val protocols: Array<out MessageProtocol> = arrayOf(FlashImageProtocol(), TextProtocol())

    @BeforeEach
    fun `init group`() {
        defaultTarget = bot.addGroup(123, 1230003).apply {
            addMember(1230003, "user3", MemberPermission.OWNER)
        }
    }

    @Test
    fun `group FlashImage receive from Android client`() {
        buildCodingChecks {
            elem(
                net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.Elem(
                    commonElem = net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.CommonElem(
                        serviceType = 3,
                        pbElem = "0A 8B 01 12 24 46 39 37 36 34 36 42 42 41 36 44 32 37 30 34 34 36 30 43 36 35 45 35 38 34 35 41 32 31 42 34 45 2E 6A 70 67 38 F3 8C D3 FB 0B 40 F3 D6 89 8F 08 48 BB 03 50 42 5A 10 77 51 32 64 65 6E 70 45 62 45 50 68 65 4D 35 73 60 01 6A 10 F9 76 46 BB A6 D2 70 44 60 C6 5E 58 45 A2 1B 4E 88 01 00 A0 01 E8 07 B0 01 B8 08 B8 01 BB 08 C0 01 C8 01 C8 01 97 F4 05 D0 01 00 E8 01 00 F0 01 00 92 02 0A 08 00 10 00 32 00 50 00 78 06".hexToBytes(),
                    ),
                )
            )
            elem(
                net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.Elem(
                    text = net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.Text(
                        str = "[闪照]请使用新版手机QQ查看闪照。",
                    ),
                )
            )
            message(
                net.mamoe.mirai.message.data.FlashImage(
                    image = net.mamoe.mirai.internal.message.image.OnlineGroupImageImpl(
                        delegate = net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.CustomFace(
                            filePath = "F97646BBA6D2704460C65E5845A21B4E.jpg",
                            fileId = -1082866061,
                            serverIp = -2115867789,
                            serverPort = 443,
                            fileType = 66,
                            signature = "wQ2denpEbEPheM5s".toByteArray(), /* 77 51 32 64 65 6E 70 45 62 45 50 68 65 4D 35 73 */
                            useful = 1,
                            picMd5 = "F9 76 46 BB A6 D2 70 44 60 C6 5E 58 45 A2 1B 4E".hexToBytes(),
                            imageType = 1000,
                            width = 1080,
                            height = 1083,
                            source = 200,
                            size = 96791,
                            pbReserve = "  2 P x".toByteArray(), /* 08 00 10 00 32 00 50 00 78 06 */
                        ),
                    ),
                )
            )
        }.doDecoderChecks()
    }

    @Test
    fun `group FlashImage send`() {
        buildCodingChecks {
            elem(
                net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.Elem(
                    commonElem = net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.CommonElem(
                        serviceType = 3,
                        pbElem = "0A 43 12 2A 7B 46 39 37 36 34 36 42 42 2D 41 36 44 32 2D 37 30 34 34 2D 36 30 43 36 2D 35 45 35 38 34 35 41 32 31 42 34 45 7D 2E 6A 70 67 6A 10 F9 76 46 BB A6 D2 70 44 60 C6 5E 58 45 A2 1B 4E 92 02 02 78 06".hexToBytes(),
                    ),
                )
            )
            elem(
                net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.Elem(
                    text = net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.Text(
                        str = "[闪照]请使用新版手机QQ查看闪照。",
                    ),
                )
            )
            message(
                net.mamoe.mirai.message.data.FlashImage(
                    image = net.mamoe.mirai.internal.message.image.OnlineGroupImageImpl(
                        delegate = net.mamoe.mirai.internal.network.protocol.data.proto.ImMsgBody.CustomFace(
                            filePath = "F97646BBA6D2704460C65E5845A21B4E.jpg",
                            fileId = -1082866061,
                            serverIp = -2115867789,
                            serverPort = 443,
                            fileType = 66,
                            signature = "wQ2denpEbEPheM5s".toByteArray(), /* 77 51 32 64 65 6E 70 45 62 45 50 68 65 4D 35 73 */
                            useful = 1,
                            picMd5 = "F9 76 46 BB A6 D2 70 44 60 C6 5E 58 45 A2 1B 4E".hexToBytes(),
                            imageType = 1000,
                            width = 1080,
                            height = 1083,
                            source = 200,
                            size = 96791,
                            pbReserve = "  2 P x".toByteArray(), /* 08 00 10 00 32 00 50 00 78 06 */
                        ),
                    ),
                )
            )
        }.doEncoderChecks()
    }
}