using System;
using System.Globalization;
using System.IO;
using System.Text;
using System.Xml;
using System.Xml.Serialization;

namespace Core
{
    public class XmlUtility
    {
        public static string Obj2XmlStr(object obj)
        {
            if (obj == null) return string.Empty;
            var sr = new XmlSerializer(obj.GetType());
            var sb = new StringBuilder();
            var w = new StringWriter(sb, CultureInfo.InvariantCulture);
            sr.Serialize(
                w,
                obj,
                new XmlSerializerNamespaces(new[] {new XmlQualifiedName(string.Empty)}));
            return sb.ToString();
        }

        public static T XmlStr2Obj<T>(string xml)
        {
            if (xml == null) return default(T);
            if (xml == string.Empty) return (T) Activator.CreateInstance(typeof (T));

            var reader = new StringReader(xml);
            var sr = new XmlSerializer(typeof (T));

            return (T) sr.Deserialize(reader);
        }
    }
}